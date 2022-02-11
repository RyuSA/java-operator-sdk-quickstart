package com.github.ryusa.vulture.josdk.geeter.crds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ryusa.vulture.josdk.geeter.Constants;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;

@Group(Constants.CRDS_GROUP)
@Version(Constants.CRDS_VERSION)
@ShortNames(Constants.SHORT_NAME)
@Plural(Constants.PLURAL_NAME)
public class Greeter extends CustomResource<Greeter.GreeterSpec, Greeter.GreeterStatus> implements Namespaced {

  public static final class GreeterSpec {
    @JsonProperty("message")
    private String message;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public static final class GreeterStatus {
    @JsonProperty("greeted")
    private String greeted;

    public String getGreeted() {
      return greeted;
    }

    public void setGreeted(String greeted) {
      this.greeted = greeted;
    }
  }

  @Override
  protected GreeterStatus initStatus() {
    var s = new GreeterStatus();
    s.setGreeted("not yet");
    return s;
  }
}
