(ns task-manager.kill-task
  (:require [task-manager.utils :as utils]))

(defn all
  [task-manager]
  [])

(defn- by-field
  [task-manager field value]
  (filter #(not= (get % field) value) task-manager))

(defn by-priority
  [task-manager priority]
  (by-field task-manager :priority (utils/get-priority priority)))

(defn by-pid
  [task-manager pid]
  (by-field task-manager :pid pid))
