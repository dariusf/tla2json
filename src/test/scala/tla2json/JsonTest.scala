package tla2json

import sourcecode.Line
import tla2json.TestUtil._
import utest._

object JsonTest extends TestSuite {
  import Value._

  private def spotCheckTestData(td: TestData, stepNo: Int)(expect: String)(implicit l: Line): Unit = {
    val actual = td.traceJson.no(stepNo).state.toJson
    assertJson(actual, expect)
  }

  private def assertValueToJson(value: String, expect: String)(implicit l: Line): Unit =
    assertValueToJson(Value.parse(value), expect)

  private def assertValueToJson(value: Value, expect: String)(implicit l: Line): Unit =
    assertJson(value.toJson, expect)

  override def tests = Tests {

    "@@" - {
      val w1 = ModelValue("w1") :> Nat(2)
      val w2 = ModelValue("w2") :> Nat(4)
      val w3 = ModelValue("w3") :> Nat(6)
      val ws = w1 @@ w2 @@ w3
      assertJson(ws.toJson, """ {"w1": 2, "w2": 4, "w3": 6} """)
    }

    "b1" - {
      assertValueToJson(
        "(ls :> [isEmpty |-> FALSE, get |-> {}])",
        """{"ls": {"isEmpty": false, "get": {"content": [], "type": "set"}}}"""
      )
    }

    "td1_1" - spotCheckTestData(TestData1, 1)(
      """{
        |  "browsers":{"b1":[]},
        |  "network":[],
        |  "tabs":{"t2":{"status":"-"},"t1":{"status":"-"}},
        |  "remote":{"type": "set", "content": []},
        |  "workers":{"w2":{"status":"-"},"w1":{"status":"-"}}
        |}
        |""".stripMargin
    )

    "td1_41" - spotCheckTestData(TestData1, 41)(
      """{
        |  "network":[
        |    {"drafts": {"type": "set", "content": [{"worker":"w1","time":1,"prov":{"w2":1,"w1":0}},{"worker":"w2","time":1,"prov":{"w2":0,"w1":0}}]}, "type":"sync:R->T","from":"Remote","to":"t1"},
        |    {"type":"ack:T->W","from":"t2","to":"w1","id":2}
        |  ],
        |  "workers":{
        |    "w2":{
        |      "drafts":{"type": "set", "content": [{"worker":"w1","time":1,"prov":{"w2":0,"w1":0}},{"worker":"w2","time":1,"prov":{"w2":0,"w1":0}}] },
        |      "time":2,
        |      "status":"live",
        |      "browser":"b1",
        |      "sync":{"Remote":{"desired":2,"lastReq":2,"lastAck":2}}
        |    },
        |    "w1":{
        |      "drafts":{"type": "set", "content": [{"worker":"w1","time":1,"prov":{"w2":1,"w1":0}}]},
        |      "time":2,
        |      "status":"live",
        |      "browser":"b1",
        |      "sync":{"Remote":{"desired":2,"lastReq":2,"lastAck":1}}
        |    }
        |  }
        |}
        |""".stripMargin.trim
    )

    "td3_1" - spotCheckTestData(TestData3, 1)(
      """{
         |  "browsers":{"b1":{"ls":{"isEmpty": false, "get": {"content": [], "type": "set"}}}, "b2":{"ls":{"isEmpty": false, "get": {"content": [], "type": "set"}}}},
         |  "network":[],
         |  "tabs":{"t2":{"status":"-"},"t1":{"status":"-"}},
         |  "remote":{"content": [], "type": "set"},
         |  "workers":{"w2":{"status":"-"},"w1":{"status":"-"}}
         |}
        |""".stripMargin
    )

  }
}
