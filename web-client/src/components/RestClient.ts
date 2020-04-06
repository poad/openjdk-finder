import fetch from 'node-fetch'
import config from './Config'
import { OpenJDK } from '../store/openjdk/types'

class RestClient {
  endpoint = config.endpoint

  async fetchList(): Promise<Array<OpenJDK>> {
    const json = await fetch(this.endpoint, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<OpenJDK>
  }
}

export default RestClient
