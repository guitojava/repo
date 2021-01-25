/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.api;

/**
 * Here we put the large swagger docs
 */
public class DealAPIDocs {

// Use   http://buildmystring.com/

    final static String  processDocs = "Process a I066XML for Accounts receivable";

    final static String  statusDocs = "" +
            "Query the status of a payment" +
            "\n" +
            "\nExample" +
            "\n" +
            "\n\t  http://<SERVER>:<PORT>/sys/v1/payments/status?accountNumber=A-001&documentNumber=12345&documentType=I&documentDate=2018-02-04&senderEntityCode=SENDERCODE" +
            "";

}
