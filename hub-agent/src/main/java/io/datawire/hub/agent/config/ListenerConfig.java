/*
 * Copyright 2015 Datawire. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.datawire.hub.agent.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListenerConfig {

  private final String bindAddress;
  private final Integer port;

  @JsonCreator
  public ListenerConfig(
      @JsonProperty("bindAddress") String bindAddress,
      @JsonProperty("port") Integer port
  ) {
    this.bindAddress = bindAddress;
    this.port = port;
  }

  public String getBindAddress() {
    return bindAddress;
  }

  public int getPort() {
    return port;
  }
}
