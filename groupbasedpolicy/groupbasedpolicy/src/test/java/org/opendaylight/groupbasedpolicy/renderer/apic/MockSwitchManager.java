/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.groupbasedpolicy.renderer.apic;

import java.util.Set;

import org.opendaylight.yang.gen.v1.urn.opendaylight.groupbasedpolicy.apic.rev140528.APicNodeConfig;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;

/**
 * Mock version of switch manager suitable for unit tests
 */
public class MockSwitchManager extends SwitchManager {

    public MockSwitchManager() {
        super(null, null);
    }

    public void addSwitch(NodeId node, 
                          NodeConnectorId tunnelPort,
                          Set<NodeConnectorId> externalPorts,
                          APicNodeConfig nodeConfig) {
        SwitchState state = new SwitchState(node, tunnelPort, 
                                            externalPorts, nodeConfig);
        state.status = SwitchStatus.READY;
        switches.put(node, state);
        for (SwitchListener listener : listeners) {
            listener.switchReady(node);
        }
    }
}
