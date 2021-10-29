(ns task-manager.utils)

(defn uuid [] (.toString (java.util.UUID/randomUUID)))
