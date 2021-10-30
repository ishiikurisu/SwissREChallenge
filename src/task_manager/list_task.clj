(ns task-manager.list-task
  (:require [task-manager.utils :as utils]))

(defn by-creation-time
  [task-manager]
  (sort #(< 0 (.compareTo (:creation-time %1) (:creation-time %2))) task-manager))
