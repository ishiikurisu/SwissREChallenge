(ns task-manager.model
  (:require [task-manager.utils :as utils]))

(defn- get-priority
  [priority]
  (get {:high 1 :medium 2 :low 3} priority 4))

(defn new-task
  [priority command]
  {:pid (utils/uuid)
   :priority (get-priority priority)
   :creation-time (new java.util.Date)
   :command command})

(defn new-task-manager
  []
  [])
