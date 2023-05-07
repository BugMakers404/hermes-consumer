db.getSiblingDB("hermes").createUser(
    {
      user: "hermes_test",
      pwd: "hermes_test",
      roles: [
        {
          role: "dbOwner",
          db: "hermes"
        }
      ]
    }
);

