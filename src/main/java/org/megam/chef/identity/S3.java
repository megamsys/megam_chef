/* 
 ** Copyright [2012-2013] [Megam Systems]
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */
package org.megam.chef.identity;

import java.io.File;

import org.megam.chef.Constants;
import org.megam.chef.core.DefaultProvisioningServiceWithShell;
import org.megam.chef.exception.ProvisionerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * @author ram
 * 
 */
public class S3 {

	private static Logger logger = LoggerFactory.getLogger(S3.class);


	public static void download(String vl) throws ProvisionerException {

		String bucketName = Constants.BUCKET_NAME;
		AWSCredentials credentials = new BasicAWSCredentials(Constants.MEGAM_AWS_ACCESS_KEY,
				Constants.MEGAM_AWS_SECRET_ID);
		AmazonS3 conn = new AmazonS3Client(credentials);
		conn.setEndpoint("s3-ap-southeast-1.amazonaws.com");
		logger.debug("-------> Amazon S3Client created");
		logger.debug("-------> Download started.....");
		logger.debug("--------------------------------------- ");		
		System.out.println("Listing objects"+vl);
		ObjectListing objectListing = conn.listObjects(new ListObjectsRequest()
				.withBucketName(bucketName).withPrefix(vl));
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			if (objectSummary.getSize() > 0) {
				System.out.println(" - " + Constants.MEGAM_VAULT+objectSummary.getKey() + "  "
						+ "(size = " + objectSummary.getSize() + ")");
				conn.getObject(new GetObjectRequest(bucketName,
						objectSummary.getKey()), new File(
								Constants.MEGAM_VAULT + bucketName + java.io.File.separator + objectSummary.getKey()));
			}
		}		
		
		logger.debug("-------> Files Download completed.....");
	}
}
