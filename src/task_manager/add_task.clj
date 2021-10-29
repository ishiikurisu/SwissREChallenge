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
