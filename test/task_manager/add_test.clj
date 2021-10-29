(ns task-manager.add-test
  (:require [clojure.test :refer :all]
            [task-manager.utils :as utils]
            [task-manager.model :as model]
            [task-manager.add-task :as add-task]))

(deftest add-default-behavior
  (testing "can add task when there is still space on task manager"
    (let [task-manager (model/new-task-manager)
          task (model/new-task :low "echo Hello!")
          task-manager (add-task/default-add task-manager task)]
      (is (= 1 (count task-manager)))))
  (testing "can't add task when the task manager is full"
    (let [task-manager (nth (iterate #(add-task/default-add %1 (model/new-task :low "echo Hello!"))
                                     (model/new-task-manager))
                            (* 2 utils/total-tasks))]
      (is (= utils/total-tasks (count task-manager))))))

(deftest add-fifo-behavior
  (testing "can add task following fifo behavior"
    (let [task (model/new-task :high "echo Goodbye!")
          addition (fn [tm] (add-task/default-add tm (model/new-task :low "echo Hello!")))
          repeated-additions (iterate addition (model/new-task-manager))
          task-manager (-> repeated-additions
                           (nth utils/total-tasks)
                           (add-task/fifo-add task))
          last-task (nth task-manager (- utils/total-tasks 1))]
      (is (= utils/total-tasks (count task-manager)))
      (is (= (:id task) (:id last-task))))))
