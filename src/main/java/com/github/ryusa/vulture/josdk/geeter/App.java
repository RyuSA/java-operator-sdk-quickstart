/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.ryusa.vulture.josdk.geeter;

import com.github.ryusa.vulture.josdk.geeter.reconcilers.GreeterReconciler;

import io.javaoperatorsdk.operator.Operator;
import io.javaoperatorsdk.operator.api.config.ConfigurationService;
import io.javaoperatorsdk.operator.api.config.ConfigurationServiceOverrider;
import io.javaoperatorsdk.operator.config.runtime.DefaultConfigurationService;

public class App {
  public static void main(String[] args) {
    // Operatorの設定を作成(デフォルト値をOverrideして実装する場合はこれ)
    ConfigurationService config = ConfigurationServiceOverrider
        .override(DefaultConfigurationService.instance())
        .withConcurrentReconciliationThreads(5)
        .build();

    Operator operator = new Operator(config);
    operator.register(new GreeterReconciler()); // Controllerを登録する
    operator.start();
  }
}
