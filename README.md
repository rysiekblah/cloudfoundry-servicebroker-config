# cloudfoundry-servicebroker-config
Library loads YAML catalog configuration for CloudFoundry Service Broker API

## References

- Catalog confiuration reference: https://docs.cloudfoundry.org/services/api.html#catalog-mgmt
- Dashboard Client reference: https://docs.cloudfoundry.org/services/api.html#DObject
- Plan reference: https://docs.cloudfoundry.org/services/api.html#PObject
- Metadata reference: https://docs.cloudfoundry.org/services/catalog-metadata.html

## Details about YAML configuration schema

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
        metadata: // optional:object
          // refer to: https://docs.cloudfoundry.org/services/catalog-metadata.html
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
        plan_updatable: optional:boolean
        plans: // mandatory:object
        - id: mandatory:string - Using a GUID is recommended.
          name: mandatory:string - all lowercase, no spaces, unique within the service
          description: mandatory:string - A short description of the plan.
          metadata: // optional:object
            // refer to: https://docs.cloudfoundry.org/services/catalog-metadata.html#plan-metadata-fields
            bullets: // array[string]
            - example-bullet#1
            - example-bullet#2
            costs: // object
            // amount: { usd: float }, unit: string
            // Example
            - amount:
                cny: 120
              unit: MONTHLY
            - amount:
                cny: 699
              unit: YEARLY
            free: optional:boolean - When false, instances of this plan have a cost. The default is true
            bindable: optional:boolean -  If specified, this takes precedence over the bindable attribute of the service.
```

## Usage

TBD

## Limitations

TBD
