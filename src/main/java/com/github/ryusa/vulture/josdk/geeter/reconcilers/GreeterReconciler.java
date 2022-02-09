package com.github.ryusa.vulture.josdk.geeter.reconcilers;

import com.github.ryusa.vulture.josdk.geeter.crds.Greeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.DeleteControl;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

@ControllerConfiguration
public class GreeterReconciler implements Reconciler<Greeter> {

  private final Logger logger = LoggerFactory.getLogger(GreeterReconciler.class);

  @Override
  public DeleteControl cleanup(Greeter resource, Context context) {
    logger.info("Cleaning up for: {} {}", resource.getMetadata().getNamespace(),
        resource.getMetadata().getName());
    return DeleteControl.defaultDelete();
  }

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
