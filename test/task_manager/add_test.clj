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
    (let [task-manager (model/new-task-manager)
          task (model/new-task :low "echo Hello!")
          task-manager (nth (iterate #(add-task/default-add %1 task)
                                     task-manager)
                            (* 2 utils/total-tasks))]
      (is (= utils/total-tasks (count task-manager)))
    )
  )
)
