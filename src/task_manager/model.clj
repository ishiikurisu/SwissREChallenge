(ns task-manager.model
  (:require [task-manager.utils :as utils]))

(defn new-task
  [priority command]
  {:pid (utils/uuid)
   :priority (utils/get-priority priority)
   :creation-time (new java.util.Date)
   :command command})

(defn new-task-manager
  []
  [])
