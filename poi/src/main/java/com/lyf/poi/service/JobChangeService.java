package com.lyf.poi.service;

import org.springframework.web.multipart.MultipartFile;

public interface JobChangeService {
    Object batch(MultipartFile file);
}
