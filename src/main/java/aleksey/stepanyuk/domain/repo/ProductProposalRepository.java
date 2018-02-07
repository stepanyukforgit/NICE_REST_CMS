package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.ProductProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductProposalRepository extends JpaRepository<ProductProposal, Long> {

    List<ProductProposal> findByProductId(Long productId);
}