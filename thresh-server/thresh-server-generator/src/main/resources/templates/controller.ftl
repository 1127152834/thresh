package ${basePackage}.${controllerPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.exception.ThreshException;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@RestController
@RequestMapping("${className?uncap_first}")
@RequiredArgsConstructor
public class ${className}Controller {

    private final I${className}Service ${className?uncap_first}Service;

    @GetMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:list')")
    public ThreshResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new ThreshResponse().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('${className?uncap_first}:list')")
    public ThreshResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new ThreshResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:add')")
    public void add${className}(@Valid ${className} ${className?uncap_first}) throws ThreshException {
        try {
            this.${className?uncap_first}Service.create${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "??????${className}??????";
            log.error(message, e);
            throw new ThreshException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:delete')")
    public void delete${className}(${className} ${className?uncap_first}) throws ThreshException {
        try {
            this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "??????${className}??????";
            log.error(message, e);
            throw new ThreshException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:update')")
    public void update${className}(${className} ${className?uncap_first}) throws ThreshException {
        try {
            this.${className?uncap_first}Service.update${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "??????${className}??????";
            log.error(message, e);
            throw new ThreshException(message);
        }
    }
}
