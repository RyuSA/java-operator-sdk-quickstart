Java Operator SDKサンプルプロジェクト
===

このリポジトリはJavaでKubernetes Operatorを実装するSDKであるJOSDKを使い、カスタムリソースを通じてGreetingするだけのOperatorを実装しています。

Java Operator SDK(JPSDK): https://javaoperatorsdk.io/

## 体験してみる
このプロジェクトをビルドするにはJava 8以上が実行できる環境が必要です。

```bash
# 適当なKubernetesクラスターを用意しておく
$ minikube start
$ make build
$ ./gradlew run

# Clean up
$ make clean
```
