# cloudfoundry-servicebroker-config

Library loads YAML configuration for CloudFoundry Service Broker API

```YAML
cloudfoundry:
  servicebroker:
    catalog:
      services:
      - name: mandatory:string - all lowercase, no spaces, unique within a platform marketplace.
        id: mandatory:string - GUID is recommended
        description: mandatory:string - A short description of the service.
        tags: optional:array[string] -  E.g. mysql, relational, redis, key-value, caching, messaging, amqp
        requires: optional:array[string] - syslog_drain, route_forwarding and volume_mount
        bindable: mandatory:boolean - Specifies whether instances of the service can be bound to app
        metadata: // A list of metadata for a service offering.
          // Example metadata
          displayName: Service_1 Display name
          imageUrl: http://example1.com/image.png
          longDescription: servce long description
          providerDisplayName: service_1 provices's name
          documentationUrl: github.com/rysiekblah
          supportUrl: rysiekblah.com/support
          provider:
            name: Service provider - name
            wechat: kozlowst
          sales:
            phone: +86 186 2345 6778
            emial: tkozlowski007@gmail.com
        dashboard: // dashboard_client optional, but has defined fields
          id: string - The id of the Oauth client that the dashboard will use.
          secret: string - A secret for the dashboard client
          redirect_uri: string - A URI for the service dashboard. Validated by the OAuth token server when the dashboard requests a token.
        plan_updatable: true
        plans:
        - id: basic
          name: basic plan for map
          description: this is basic plan
          metadata:
            bullets:
            - feature#1
            - feature#1
            costs:
            - amount:
                cny: 120
              unit: MONTHLY
            - amount:
                cny: 699
              unit: YEARLY
            free: true
            bindable: true
```
