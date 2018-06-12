package com.example.util;

import com.example.enums.ResultEnum;
import com.example.result.ResultVO;

/**
 * @author xuan
 * @create 2018-06-07 14:12
 **/
public class ResultVOUtil {

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
