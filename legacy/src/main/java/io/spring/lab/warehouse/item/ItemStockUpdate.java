package io.spring.lab.warehouse.item;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
@ToString
public class ItemStockUpdate {

	private final long id;

	private final int countDiff;

	static ItemStockUpdate of(int countDiff) {
		return new ItemStockUpdate(0, countDiff);
	}

	ItemStockUpdate withId(long id) {
		return new ItemStockUpdate(id, countDiff);
	}

	int applyFor(Item item) {
		int count = item.getCount();

		if (count + countDiff < 0) {
			throw new OutOfStock(item, countDiff);
		}

		return count + countDiff;
	}
}
