
.PHONY: all clean deploy
.DEFAULT_GOAL := all

clean:
	kubectl delete -f k8s
	kubectl delete -f build/classes/java/main/META-INF/fabric8/greeters.com.github.ryusa.vulture-v1.yml

deploy:
	./gradlew build
	kubectl apply -f build/classes/java/main/META-INF/fabric8/greeters.com.github.ryusa.vulture-v1.yml 
	kubectl apply -f k8s/

all: clean deploy
