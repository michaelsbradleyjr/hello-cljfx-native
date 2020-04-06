(ns hello-cljfx-native.core
  (:require [cljfx.api :as fx]
            [cljfx.fx.label :as label]
            [cljfx.fx.scene :as scene]
            [cljfx.fx.stage :as stage]
            [cljfx.fx.v-box :as v-box])
  (:gen-class)
  (:import [javafx.application Platform]))

(def message (atom "world!"))

(defn hello []
  (fx/on-fx-thread
   (fx/create-component
    {:fx/type :stage
     :showing true
     :title "Cljfx example"
     :width 300
     :height 100
     :scene {:fx/type :scene
             :root {:fx/type :v-box
                    :alignment :center
                    :children [{:fx/type :label
                                :text (str "Hello, " @message)}]}}})))

(defn -main [& args]
  (Platform/setImplicitExit true)
  (hello))
