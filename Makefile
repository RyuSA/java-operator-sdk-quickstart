
.PHONY: clean deploy container-build

clean:
	kubectl delete -f k8s
	kubectl delete -f build/classes/java/main/META-INF/fabric8/greeters.vulture.ryusa.github.com-v1.yml

deploy:
	./gradlew build
	kubectl apply -f build/classes/java/main/META-INF/fabric8/greeters.vulture.ryusa.github.com-v1.yml
	kubectl apply -f k8s/

container-build:
	./gradlew jibDockerBuild --image josdk-greeter
