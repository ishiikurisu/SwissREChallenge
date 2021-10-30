(ns task-manager.list-test
  (:require [clojure.test :refer :all]
            [task-manager.utils :as utils]
            [task-manager.model :as model]
            [task-manager.add-task :as add-task]
            [task-manager.list-task :as list-task])
  (:import (java.util Date Calendar)))

(defn- add-1-hour
  [date]
  (let [calendar (Calendar/getInstance)]
    (do (.setTime calendar date)
        (.add calendar Calendar/HOUR 1)
        (.getTime calendar))))

(defn- new-random-task
  [date]
  (let [priority (nth [:low :medium :high] (-> 3 rand int))
        command (str "echo " (rand))
        task (model/new-task priority command)]
    (assoc task :creation-time date)))

(deftest add-time-to-date
  (testing "can add time to date"
    (let [now (new Date)
          after (add-1-hour now)]
      (is (= 1 (.compareTo after now)))))) ; 1 means >

(defn- fill-task-manager
  "Fills the task manager with random tasks"
  [task-manager]
  (let [now (new Date)]
    (loop [tm task-manager
           i utils/total-tasks
           date (add-1-hour now)]
      (if (= i 0)
        tm
        (recur (add-task/default-add tm (new-random-task date))
               (dec i)
               (add-1-hour date))))))

(defn- verify-sort
  [listed-tasks comparison-function]
  (loop [head-task (first listed-tasks)
         tail-tasks (next listed-tasks)]
    (let [second-task (first tail-tasks)
          result (comparison-function head-task second-task)]
      (if (or (= 1 (count tail-tasks)) (false? result))
        result
        (recur second-task
               (next tail-tasks))))))

(deftest list-tasks-by-creation-time
  (testing "can list tasks by creation time"
    (let [task-manager (fill-task-manager (model/new-task-manager))
          listed-tasks (list-task/by-creation-time task-manager)
          are-ordered (verify-sort listed-tasks
                                   #(< 0 (.compareTo (:creation-time %1)
                                                     (:creation-time %2))))]
      (is (true? are-ordered)))))

(deftest list-tasks-by-priority
  (testing "can list tasks by priority"
    (let [task-manager (fill-task-manager (model/new-task-manager))
          listed-tasks (list-task/by-priority task-manager)
          are-ordered (verify-sort listed-tasks
                                   #(>= (:priority %1) (:priority %2)))]
      (is (true? are-ordered)))))

(deftest list-tasks-by-pid
  (testing "can list tasks by PID"
    (let [task-manager (fill-task-manager (model/new-task-manager))
          listed-tasks (list-task/by-pid task-manager)
          are-ordered (verify-sort listed-tasks
                                   #(< 0 (.compareTo (:pid %1)
                                                     (:pid %2))))]
      (is (true? are-ordered)))))
