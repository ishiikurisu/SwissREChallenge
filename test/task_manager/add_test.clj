(ns task-manager.add-test
  (:require [clojure.test :refer :all]
            [task-manager.utils :as utils]
            [task-manager.model :as model]
            [task-manager.add-task :as add-task]))

(defn- fill-task-manager-loop
  [task-manager priority i]
  (if (> i 0)
    (fill-task-manager-loop (add-task/default-add task-manager (model/new-task priority "echo Hello!"))
                            priority
                            (- i 1))
    task-manager))

(defn- fill-task-manager
  [task-manager priority]
  (fill-task-manager-loop task-manager priority (* 2 utils/total-tasks)))

(deftest add-default-behavior
  (testing "can add task when there is still space on task manager"
    (let [task-manager (model/new-task-manager)
          task (model/new-task :low "echo Hello!")
          task-manager (add-task/default-add task-manager task)]
      (is (= 1 (count task-manager)))))
  (testing "can't add task when the task manager is full"
    (let [task-manager (-> (model/new-task-manager)
                           (fill-task-manager :low))]
      (is (= utils/total-tasks (count task-manager))))))

(deftest add-fifo-behavior
  (testing "can add task following fifo behavior"
    (let [task (model/new-task :high "echo Goodbye!")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager :low)
                           (add-task/fifo-add task))
          task-found (= (:pid task) (:pid (nth task-manager (dec utils/total-tasks))))]
      (is (= utils/total-tasks (count task-manager)))
      (is (true? task-found)))))

(deftest add-priority-behavior
  (testing "following priority behavior, should add tasks of higher priority"
    (let [task (model/new-task :high "echo Goodbye!")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager :medium)
                           (add-task/priority-add task))
          task-found (reduce #(or %1 (= (:id task) (:id %2))) false task-manager)]
      (is (= utils/total-tasks (count task-manager)))
      (is (true? task-found))))
  (testing "following priority behavior should not add tasks of lower priority"
    (let [task (model/new-task :low "echo Goodbye!")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager :medium)
                           (add-task/priority-add task))
          task-found (->> task-manager
                          (map #(= (:pid task) (:pid %1)))
                          (filter true?)
                          (true?))]
      (is (= utils/total-tasks (count task-manager)))
      (is (false? task-found)))))

(deftest sort-comparison
  (testing "Verify if sort comparison works as expected"
    (let [s [7 5 3 9 5 1]
          sorted-s (sort > s)]
      (is (> (nth sorted-s 0) (nth sorted-s 1)) ))))
