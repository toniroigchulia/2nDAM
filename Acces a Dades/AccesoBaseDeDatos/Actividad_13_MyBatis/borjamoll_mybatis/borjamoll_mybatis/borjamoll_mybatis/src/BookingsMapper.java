import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Mapeador de datos
 * 
 */
public interface BookingsMapper {
    void carregarDadesXML(
            @Param("locationNumber") int locationNumber,
            @Param("clientId") int clientId,
            @Param("clientName") String clientName,
            @Param("agencyId") int agencyId,
            @Param("agencyName") String agencyName,
            @Param("price") BigDecimal price,
            @Param("roomTypeId") int roomTypeId,
            @Param("hotelName") String hotelName,
            @Param("checkIn") LocalDate checkIn,
            @Param("roomNights") int roomNights);

}