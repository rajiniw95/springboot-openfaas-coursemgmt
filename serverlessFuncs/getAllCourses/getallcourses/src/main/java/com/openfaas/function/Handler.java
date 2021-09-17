package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

//import com.coursemgmt.courses.model.Course;
//import com.coursemgmt.courses.repository.CourseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import com.coursemgmt.courses.service.CourseServiceImpl;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {
        Response res = new Response();
	    res.setBody("Handler for get all courses");

	    return res;
    }
}
