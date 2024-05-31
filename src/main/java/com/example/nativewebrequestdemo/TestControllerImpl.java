package com.example.nativewebrequestdemo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * This is how OpenAPI generator does things. It generates an interface that contains methods with
 * underscores that contain the @RequestMapping etc. annotations. The user implements the interface
 * and overrides the non-underscore methods to implement the functionality. The access to request
 * is via NativeWebRequest proxy injected via constructor.
 */
@RestController
@RequestMapping(path = "/")
public class TestControllerImpl implements TestController {
    private final NativeWebRequest nativeWebRequest;

    @Autowired
    public TestControllerImpl(NativeWebRequest nativeWebRequest /*<-- This has red underline because IntelliJ IDEA does not know about this bean/proxy. */) {
        this.nativeWebRequest = nativeWebRequest;
    }

    @Override
    public ResponseEntity<String> get() {
        HttpServletRequest httpServletRequest = this.nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.ok("Hello World from " + httpServletRequest.getRequestURI());
    }
}
