package lb.hl.product.dto;


import com.lb.common.util.PageQueryBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 查询产品列表参数
 * </p>
 *
 * @author hl
 * @since 2024-03-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Product对象", description = "Product对象")
public class ProductQueryDto extends PageQueryBean {

}
