(ns task-manager.kill-test
  (:require [clojure.test :refer :all]
            [task-manager.utils :as utils]
            [task-manager.model :as model]
            [task-manager.add-task :as add-task]
            [task-manager.kill-task :as kill-task]))

(defn- new-random-task []
  (let [priority (nth [:low :medium :high] (-> 2 rand int))
        command (str "echo " (rand))
        task (model/new-task priority command)]
    task))

(defn- fill-task-manager
  [task-manager]
  (loop [i (inc utils/total-tasks)
         tm task-manager]
    (if (= i 0)
      tm
      (recur (dec i)
             (add-task/default-add tm (new-random-task))))))

(deftest kill-all-processes
  (testing "can kill all processes"
    (let [task-manager (-> (model/new-task-manager)
                           (fill-task-manager)
                           (kill-task/all))]
      (is (= 0 (count task-manager))))))

(defn- contains-task?
  [task-manager task]
  (reduce #(or %1 %2) (map #(= (:pid %) (:pid task)) task-manager)))

(deftest kill-specific-process
  (testing "can kill task by PID"
    (let [task (model/new-task :high "ls /")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager)
                           (add-task/fifo-add task))]
      (is (contains-task? task-manager task))
      (is (false? (contains-task? (kill-task/by-pid task-manager (:pid task)) task)))))
  (testing "killing tasks by inexistent PIDs don't affect the task manager"
    (let [task (model/new-task :high "ls /")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager))]
      (is (false? (contains-task? task-manager task)))
      (is (false? (contains-task? (kill-task/by-pid task-manager (:pid task)) task)))) ; implies no error happened
  ))

(defn- contains-priority?
  [task-manager priority]
  (reduce #(or %1 %2) (map #(= (:priority %) (utils/get-priority priority)) task-manager)))

(deftest kill-processes-by-priority
  (testing "can kill tasks by priority"
    (let [priority :high
          task (model/new-task priority "ls /")
          task-manager (-> (model/new-task-manager)
                           (fill-task-manager)
                           (add-task/fifo-add task))]
      (is (contains-priority? task-manager priority))
      (is (false? (contains-priority? (kill-task/by-priority task-manager priority)
                                      priority))))))
