(ns task-manager.utils)

(def total-tasks 5)

(defn uuid [] (.toString (java.util.UUID/randomUUID)))

(defn get-priority
  [priority]
  (get {:high 1 :medium 2 :low 3} priority 4))
