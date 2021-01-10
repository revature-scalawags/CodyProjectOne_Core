import org.scalatest.flatspec.AnyFlatSpec

class Test extends AnyFlatSpec {
  "Hive" should "print invalid input if a blank string is inserted" in {
      assert(Hive.query("").equals("invalid"))
  }
  it should "return results in a text file from a successful standard query" in {
    assert(Hive.query("june").contains("963857"))
  }
  it should "run a different query with the relevant flag" in {
    assert(Hive.query("june -from").contains("963857"))
  }
}
