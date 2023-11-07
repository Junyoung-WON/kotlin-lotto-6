package lotto

class Validator {
    fun validatePurchaseMoney(purchaseMoney: String): Boolean {
        return try {
            isNumberOverZero(purchaseMoney) && isDividedUpThousand(purchaseMoney.toInt())
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e}")
            false
        }
    }

    fun isNumberOverZero(input: String): Boolean {
        val number = input.toIntOrNull()
        return if (number != null && number > 0) {
            true
        } else {
            throw IllegalArgumentException("0보다 큰 정수를 입력해주세요.")
        }
    }

    fun isDividedUpThousand(money: Int): Boolean {
        return if (money % 1000 == 0) {
            true
        } else {
            throw IllegalArgumentException("구입 금액이 1000원으로 나누어 떨어지지 않습니다.")
        }
    }

    fun validateLottoNumber(lotto: String): Boolean {
        return try {
            containsComma(lotto)
            val lottoNumbers: List<String> = lotto.split(",")
            countsLottoNumbers(lottoNumbers)
            for (number in lottoNumbers) {
                isNumberOverZero(number)
                inCorrectRange(number.toInt())
            }
            hasDuplicateNumbers(lottoNumbers)
            true
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e}")
            false
        }
    }

    fun containsComma(lotto: String) {
        if (!lotto.contains(',')) {
            throw IllegalArgumentException("로또 번호는 쉼표(,)로 구분지어주세요.")
        }
    }

    fun countsLottoNumbers(lottoNumbers: List<String>) {
        if (lottoNumbers.size != 6) {
            throw IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.")
        }
    }

    fun inCorrectRange(lottoNumber: Int) {
        if (lottoNumber < 1 && lottoNumber > 45) {
            throw IllegalArgumentException("로또 번호는 1과 45 사이의 정수여야 합니다.")
        }
    }

    fun hasDuplicateNumbers(lottoNumbers: List<String>) {
        var validator = mutableListOf<Int>()
        for (lotto in lottoNumbers) {
            if (validator.contains(lotto.toInt())){
                throw IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.")
            }
            validator.add(lotto.toInt())
        }
    }
}