(ns task-manager.model-test
  (:require [clojure.test :refer :all]
            [task-manager.model :as model]))

(deftest a
  (testing "New tasks have the desired "
    (let [priority :low
          command "echo 'Hello!'"
          task (model/new-task priority command)
          now (new java.util.Date)]
      (is (not (nil? (get task :pid))))
      (is (= 3 (get task :priority)))
      (is (= 0 (.compareTo (get task :creation-time) now))) ; means they are equal
      (is (= (get task :command) command)))))
