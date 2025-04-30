package com.anhnguyen.mtadminservice.controller.user;

import com.anhnguyen.mtadminservice.common.ErrorType;
import com.anhnguyen.mtadminservice.controller.BaseController;
import com.anhnguyen.mtadminservice.domain.response.Result;
import com.anhnguyen.mtadminservice.service.impl.MovieTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/movietime")
public class TimeMovieController extends BaseController {
    @Autowired
    private MovieTimeService movieTimeService;

    @GetMapping("/get")
    private Result getMovieTime(@RequestHeader String token,
                                   @RequestParam Integer idMovie,
                                @RequestParam Integer date){
        logger.info("[Movie Time User Get] Get time by idMovie {} and date {}",idMovie,date);
        if(token!=null && idMovie !=null && date!=null ){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = movieTimeService.getMovieTimeByIdAndDate(id,token,idMovie,date);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }
}
