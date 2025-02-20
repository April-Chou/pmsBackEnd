package com.fdm.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdm.domain.PositionDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author April Chou
 * @Classname JsonToJava
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/16 20:50
 */
@Component
public class JsonToJavaUtil {
    //        @Autowired
//        private ObjectMapper objectMapper;

    ObjectMapper mapper = new ObjectMapper();

    public PositionDto JsonFileConvertsToPositionDto(File input) throws IOException {
        return mapper.readValue(input, PositionDto.class);
    }

    public PositionDto StringConvertsToPositionDto(String input) throws IOException {
        return mapper.readValue(input, PositionDto.class);
    }

    /**
     * readValue()函数还接受其他形式的输入, 比如从JSON字符串的文件中读取数据:
     *
     * @param input
     * @return
     * @throws IOException
     */
    public PositionDto JsonStringFileConvertsToPositionDto(String input) throws IOException {
        String resource = "target/" + input;
        return mapper.readValue(new File(resource), PositionDto.class);
    }

    /**
     * 从网络获取JSON字符串文件
     * @param input
     * @return
     * @throws IOException
     */
    public PositionDto UrlConvertsToPositionDto(String input) throws IOException {
        URL resource = new URL("file:src/test/resources/"+input);
        return mapper.readValue(resource, PositionDto.class);
    }

    /**
     * 将JSON解析为Java Map：
     * JSON数组字符串解析为Java List
     *
     *
     */


}