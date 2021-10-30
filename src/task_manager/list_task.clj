(ns task-manager.list-task
  (:require [task-manager.utils :as utils]))

(defn by-creation-time
  [task-manager]
  (sort #(< 0 (.compareTo (:creation-time %1) (:creation-time %2))) task-manager))

(defn by-priority
  [task-manager]
  (sort #(> (:priority %1) (:priority %2)) task-manager))

(defn by-pid
  [task-manager]
  (sort #(< 0 (.compareTo (:pid %1) (:pid %2))) task-manager))
