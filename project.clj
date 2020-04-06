(defproject hello-cljfx-native "0.1.0-SNAPSHOT"
  :description "An attempt at a native-image build involving cljfx"
  :url "https://github.com/michaelsbradleyjr/hello-cljfx-native"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[cljfx "1.6.8"]
                 [org.clojure/clojure "1.10.2-alpha1"]]
  :plugins [[lein-shell "0.5.0"]]
  :repl-options {:init-ns hello-cljfx-native.core}
  :main hello-cljfx-native.core
  :pom-addition [:pluginRepositories
                 [:pluginRepository
                  [:id "gluon-releases"]
                  [:url "https://nexus.gluonhq.com/nexus/content/repositories/releases/"]]]
  :pom-plugins [[com.gluonhq/client-maven-plugin "0.1.22"
                 (:configuration
                  [:mainClass "hello_cljfx_native.core"]
                  [:nativeImageArgs
                   ;; [:list "-Dclojure.compiler.direct-linking=true"]
                   ;; [:list "-Dclojure.spec.skip-macros=true"]
                   ;; [:list "-H:+AllowIncompleteClasspath"]
                   ;; [:list "-H:+TraceClassInitialization"]
                   ;; [:list "--initialize-at-build-time"]
                   [:list "--initialize-at-build-time=clojure"]
                   [:list "--initialize-at-run-time=clojure.core.server__init"]
                   [:list "--no-fallback"]]
                  [:verbose "true"])]
                [org.openjfx/javafx-maven-plugin "0.0.4"
                 (:configuration
                  [:mainClass "hello_cljfx_native.core"]
                  [:options
                   [:option "-agentlib:native-image-agent=config-merge-dir=resources/META-INF/native-image"]])]]
  :plugin-repositories [["gluon-releases" "https://nexus.gluonhq.com/nexus/content/repositories/releases/"]]
  :profiles {:uberjar {:aot :all
                       :injections [(javafx.application.Platform/exit)]}}
  :clean-targets [:target-path "pom.xml"]
  :aliases {"build" ["do" ["clean"] ["uberjar"] ["pom"] ["build-native"]]
            "build-native" ["shell" "mvn" "client:build"]})
