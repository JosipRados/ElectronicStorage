package electronicstorage.BussinesLogic.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ElementAvailabilityDTO {
    long availableQuantity;
    ProcedureResponseDTO procedureResponse = new ProcedureResponseDTO();

    public long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProcedureResponseDTO getProcedureResponse() {
        return procedureResponse;
    }

    public void setProcedureResponse(ProcedureResponseDTO procedureResponse) {
        this.procedureResponse = procedureResponse;
    }
}
