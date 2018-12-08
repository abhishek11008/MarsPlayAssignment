/*
 * Copyright 2015-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package marsplay.app.com.marsplayassignment;

public class Constants {

    /*
     * You should replace these values with your own. See the README for details
     * on what to fill in.
     */
    //public static final String COGNITO_POOL_ID = "CHANGE_ME";
    public static final String COGNITO_POOL_ID = "us-west-2:66dc54a6-6f4f-4b8c-b52a-d9ff227fe8b3";

    /*
     * Region of your Cognito identity pool ID.
     */
    //public static final String COGNITO_POOL_REGION = "CHANGE_ME";
    public static final String COGNITO_POOL_REGION = "us-west-2";

    /*
     * Note, you must first create a bucket using the S3 console before running
     * the sample (https://console.aws.amazon.com/s3/). After creating a bucket,
     * put it's name in the field below.
     */
    //public static final String BUCKET_NAME = "CHANGE_ME";
    public static final String BUCKET_NAME = "mpassignment";

    /*
     * Region of your bucket.
     */
    //public static final String BUCKET_REGION = "CHANGE_ME";
    public static final String BUCKET_REGION = "us-west-2";
}