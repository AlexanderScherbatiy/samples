package calculator.classic;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassicCalculatorRepository extends PagingAndSortingRepository<ClassicCalculatorRecord, String> {
}
