(ns task-manager.add-task
  (:require [task-manager.utils :as utils]))

(defn default-add
  [task-manager task]
  (if (< (count task-manager) utils/total-tasks)
    (concat task-manager [task])
    task-manager))

(defn fifo-add
  [task-manager task]
  (if (< (count task-manager) utils/total-tasks)
    (concat task-manager [task])
    task-manager))

(defn priority-add
  [task-manager task]
  (take utils/total-tasks (sort #(< (:priority %1) (:priority %2)) (concat task-manager [task]))))
