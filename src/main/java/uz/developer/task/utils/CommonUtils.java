package uz.developer.task.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uz.developer.task.exception.BadRequestException;

public class CommonUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Sahifa soni noldan kam boʻlishi mumkin emas.");
        }

        if (size > 100) {
            throw new BadRequestException(" elementlar soni 100 dan koʻp boʻlishi mumkin emas.");
        }
    }
    public static Pageable getPageableNew(int page, int size) {
        validatePageNumberAndSize(page,size);
        return PageRequest.of(page, size);
    }
}