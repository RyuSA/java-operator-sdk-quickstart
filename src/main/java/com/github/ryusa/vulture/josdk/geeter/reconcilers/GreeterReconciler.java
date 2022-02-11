package com.github.ryusa.vulture.josdk.geeter.reconcilers;

import com.github.ryusa.vulture.josdk.geeter.crds.Greeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.DeleteControl;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

/**
 * Controller は Reconciler<R extends HasMetadata> を実装する必要があります
 * さらに ControllerConfiguration アノテーションに Controller としての設定を追加できます
 */
@ControllerConfiguration(finalizerName = "greeters.vulture.ryusa.github.com/finalizer")
public class GreeterReconciler implements Reconciler<Greeter> {

  private final Logger logger = LoggerFactory.getLogger(GreeterReconciler.class);

  /**
   * Greeterリソースが削除された際にfinalizer経由で実行されるメソッド
   */
  @Override
  public DeleteControl cleanup(Greeter resource, Context context) {
    logger.info("Cleaning up for: {} {}", resource.getMetadata().getNamespace(),
        resource.getMetadata().getName());
    return DeleteControl.defaultDelete(); // finalizerを削除して終了
  }

  /**
   * リコンサイル処理、Greeterリソースが作成されたり更新されたり削除されたりした場合に呼び出されます
   */
  @Override
  public UpdateControl<Greeter> reconcile(Greeter resource, Context context) {

    if ("Greeted".equals(resource.getStatus().getGreeted())) {
      logger.info("You have already greeted: {} {}", resource.getMetadata().getNamespace(),
          resource.getMetadata().getName());
      return UpdateControl.noUpdate();
    }

    resource.getStatus().setGreeted("Greeted");
    logger.info("Greeting!! {}", resource.getSpec().getMessage());
    return UpdateControl.updateStatus(resource);
  }
}
