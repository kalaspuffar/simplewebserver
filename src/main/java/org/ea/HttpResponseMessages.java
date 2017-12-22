package org.ea;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseMessages {
    private Map<Integer, String> responseMessages = new HashMap<>();

    private static HttpResponseMessages instance;

    private HttpResponseMessages() {
        responseMessages.put(100, "Continue");
        responseMessages.put(101, "Switching Protocols");
        responseMessages.put(102, "Processing");
        responseMessages.put(103, "Early Hints");

        responseMessages.put(200, "OK");
        responseMessages.put(201, "Created");
        responseMessages.put(202, "Accepted");
        responseMessages.put(203, "Non-Authoritative Information");
        responseMessages.put(204, "No Content");
        responseMessages.put(205, "Reset Content");
        responseMessages.put(206, "Partial Content");
        responseMessages.put(207, "Multi-Status");
        responseMessages.put(208, "Already Reported");
        responseMessages.put(226, "IM Used");

        responseMessages.put(300, "Multiple Choices");
        responseMessages.put(301, "Moved Permanently");
        responseMessages.put(302, "Found");
        responseMessages.put(303, "See Other");
        responseMessages.put(304, "Not Modified");
        responseMessages.put(305, "Use Proxy");
        responseMessages.put(306, "Switch Proxy");
        responseMessages.put(307, "Temporary Redirect");
        responseMessages.put(308, "Permanent Redirect");

        responseMessages.put(400, "Bad Request");
        responseMessages.put(401, "Unauthorized");
        responseMessages.put(402, "Payment Required");
        responseMessages.put(403, "Forbidden");
        responseMessages.put(404, "Not Found");
        responseMessages.put(405, "Method Not Allowed");
        responseMessages.put(406, "Not Acceptable");
        responseMessages.put(407, "Proxy Authentication Required");
        responseMessages.put(408, "Request Timeout");
        responseMessages.put(409, "Conflict");
        responseMessages.put(410, "Gone");
        responseMessages.put(411, "Length Required");
        responseMessages.put(412, "Precondition Failed");
        responseMessages.put(413, "Payload Too Large");
        responseMessages.put(414, "URI Too Long");
        responseMessages.put(415, "Unsupported Media Type");
        responseMessages.put(416, "Range Not Satisfiable");
        responseMessages.put(417, "Expectation Failed");
        responseMessages.put(418, "I'm a teapot");
        responseMessages.put(420, "Method Failure");
        responseMessages.put(421, "Misdirected Request");
        responseMessages.put(422, "Unprocessable Entity");
        responseMessages.put(423, "Locked");
        responseMessages.put(424, "Failed Dependency");
        responseMessages.put(426, "Upgrade Required");
        responseMessages.put(428, "Precondition Required");
        responseMessages.put(429, "Too Many Requests");
        responseMessages.put(431, "Request Header Fields Too Large");
        responseMessages.put(440, "Login Time-out");
        responseMessages.put(449, "No Response");
        responseMessages.put(450, "Blocked by Windows Parental Controls");
        responseMessages.put(451, "Unavailable For Legal Reasons");
        responseMessages.put(495, "Retry With");
        responseMessages.put(497, "SSL Certificate Error");
        responseMessages.put(498, "Invalid Token");
        responseMessages.put(499, "SSL Certificate Required");

        responseMessages.put(500, "Internal Server Error");
        responseMessages.put(501, "Not Implemented");
        responseMessages.put(502, "Bad Gateway");
        responseMessages.put(503, "Service Unavailable");
        responseMessages.put(504, "Gateway Timeout");
        responseMessages.put(505, "HTTP Version Not Supported");
        responseMessages.put(506, "Variant Also Negotiates");
        responseMessages.put(507, "Insufficient Storage");
        responseMessages.put(508, "Loop Detected");
        responseMessages.put(509, "Bandwidth Limit Exceeded");
        responseMessages.put(510, "Not Extended");
        responseMessages.put(511, "Network Authentication Required");
        responseMessages.put(520, "Unknown Error");
        responseMessages.put(521, "Web Server Is Down");
        responseMessages.put(522, "Connection Timed Out");
        responseMessages.put(523, "Origin Is Unreachable");
        responseMessages.put(524, "A Timeout Occurred");
        responseMessages.put(525, "SSL Handshake Failed");
        responseMessages.put(526, "Invalid SSL Certificate");
        responseMessages.put(527, "Railgun Error");
        responseMessages.put(530, "Site is frozen");
        responseMessages.put(598, "Network read timeout error");
    }

    public static String getResponse(int code) {
        if(instance == null) {
            instance = new HttpResponseMessages();
        }
        return instance.responseMessages.get(code);
    }
}
