/*
 * Copyright (c) 2018 Baidu, Inc. All Rights Reserved.
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

package com.baidu.brpc.naming;

import com.baidu.brpc.client.endpoint.EndPoint;
import com.baidu.brpc.test.BaseMockitoTest;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileNamingServiceTest extends BaseMockitoTest {

    @Test
    public void testAll() throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("test_server_list.txt");
        String serverList = url.getFile();
        FileNamingService namingService = new FileNamingService(new BrpcURL("file://" + serverList));

        List<EndPoint> endPoints = namingService.lookup(null);
        assertThat(endPoints.size(), is(1));
        assertThat(endPoints, hasItems(
                new EndPoint("127.0.0.1", 8002)
        ));
        namingService.unsubscribe(null);
    }
}