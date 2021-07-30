import React from 'react';
import './ClientDetails.css'
import { Table, Col, Row, Descriptions, Tag, PageHeader  } from 'antd';


const ClientDetails = ({ location: { state } }) => {
    console.log(state)

    const dataSource = state.investors.map(investor => {
        return {
            ...investor,
            key: investor.id 
        }
    })

    const columns = [
        {
            title: "Name",
            dataIndex: "name",
            key: "name"
        },
        {
            title: "Funds",
            dataIndex: "funds",
            key: "descrifundsption",
            render: (funds, row) => (
                funds.map((fund, i) => {
                    return(
                        <Tag color='green' key={fund.id}>
                            {`$${fund.amount}`}
                        </Tag>
                    )
                })  
            )
        }
    ]

    return (
        <Row>
            <Col>
                <Row justify='left'>
                    <Col span={6}>
                        <PageHeader
                            className="site-page-header"
                            onBack={() => window.history.back()}
                            title="Clients"
                        />
                    </Col>
                </Row>
                <Row justify='center'>

                    <Col span={12}>
                        <Descriptions title="Client Details">
                            <Descriptions.Item label="Name">{ state.name }</Descriptions.Item>
                            <Descriptions.Item label="Description">{ state.description }</Descriptions.Item>
                            <Descriptions.Item label="Phone Number">{ state.phoneNumber } </Descriptions.Item>
                            <Descriptions.Item label="Type">{ state.type }</Descriptions.Item>
                        </Descriptions>,
                    </Col>
                </Row>
                <Row justify='center'>
                    <Col span={12}>
                        <Table dataSource={dataSource} columns={columns} />;
                    </Col>
                </Row>
            </Col>
        </Row>
    )
}

export default ClientDetails;