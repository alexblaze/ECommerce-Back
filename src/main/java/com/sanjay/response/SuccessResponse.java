package com.sanjay.response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;
public class SuccessResponse {
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String RESULT = "data";
    private static final String COUNT = "count";
    private static final String TOTAL_ELEMENTS = "totalElements";
    private static final String NO_DATA = "no data found";
    private static final String SUCCESS = "Success";
    private static final String PAGE = "page";

    private SuccessResponse() {
    }

    public static ResponseEntity<?> configure(Object object, String message) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, message);
        addObjectToResponse(object, objectMap);
        return ResponseEntity.ok()
                .body(objectMap);
    }

    public static ResponseEntity<?> configure() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(ERROR, false);
        objectMap.put(MESSAGE, SUCCESS);
        return ResponseEntity.ok()
                .body(objectMap);
    }

    public static ResponseEntity<?> configure(Object object) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, SUCCESS);
        addObjectToResponse(object, objectMap);
        return ResponseEntity.ok()
                .body(objectMap);

    }

    public static ResponseEntity<?> configure(String message) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, message);
        objectMap.put(ERROR, false);
        return ResponseEntity.ok()
                .body(objectMap);

    }

    public static ResponseEntity<?> configure(Collection<?> collection) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, collection.isEmpty() ? NO_DATA : SUCCESS);
        objectMap.put(RESULT, collection);
        objectMap.put(COUNT, collection.size());
        objectMap.put(ERROR, false);
        return ResponseEntity.ok()
                .body(objectMap);

    }

    public static ResponseEntity<?> configure(Page<?> page) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, page.getContent().isEmpty() ? NO_DATA : SUCCESS);
        addObjectToResponse(page, objectMap);
        objectMap.put(TOTAL_ELEMENTS, page.getTotalElements());
        objectMap.put(RESULT, page.getContent());
        objectMap.put(COUNT, page.getContent().size());
        objectMap.put(ERROR, false);
        objectMap.put(PAGE, page.getNumber());
        return ResponseEntity.ok()
                .body(objectMap);
    }


    private static void addObjectToResponse(Object object, Map<String, Object> objectMap) {
        objectMap.put(ERROR, false);

        if (object instanceof ArrayList && !((ArrayList<?>) object).isEmpty()) {
            List<Object> objectList = new ArrayList<>(((ArrayList<?>) object));
            objectMap.put(RESULT, objectList);
            objectMap.put(COUNT, objectList.size());
        } else if (object instanceof ArrayList) {
            objectMap.put(MESSAGE, NO_DATA);
            objectMap.put(RESULT, new ArrayList<>());
        } else {
            objectMap.put(RESULT, object);
            objectMap.put(COUNT, object == null ? 0 : 1);
        }

    }
}
