import React from 'react'
import { Layout } from '../components/Layout';
import OpenJDKList from '../components/OpenJDKList'

class Home extends React.Component {
  render() {
    return (
      <section>
        <Layout>
          <div className="hero">
            <OpenJDKList items={[]} page={{ page: 0, rowsPerPage: 10}}></OpenJDKList>
          </div>
        </Layout>
      </section>
    )
  }
}

export default Home
