package com.github.ryusa.vulture.josdk.geeter.crds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ryusa.vulture.josdk.geeter.Constants;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * CRD(Custom Resource Definition)を表現するPOJO
 * アノテーションにCRDのメタ情報を記述しておくことでCRDを生成できます
 * 
 * apiVersion: vulture.ryusa.github.com/v1alpha1
 * kind: Greeter
 * metadata:
 * name: $name
 * spec:
 * message: $string
 * status:
 * greeted: $status
 */
@Group(Constants.CRDS_GROUP)
@Version(Constants.CRDS_VERSION)
@ShortNames(Constants.SHORT_NAME) // kubectl get gr
@Plural(Constants.PLURAL_NAME) // APIエンドポイントの名前
public class Greeter extends CustomResource<Greeter.GreeterSpec, Greeter.GreeterStatus> implements Namespaced {

  @Getter
  @Setter
  public static final class GreeterSpec {
    @JsonProperty("message")
    private String message;
  }

  @Getter
  @Setter
  public static final class GreeterStatus {
    @JsonProperty("greeted")
    private String greeted;
  }

  @Override
  protected GreeterStatus initStatus() {
    var s = new GreeterStatus();
    s.setGreeted("not yet");
    return s;
  }
}
